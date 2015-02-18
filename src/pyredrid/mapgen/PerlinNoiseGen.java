package pyredrid.mapgen;

import java.util.Random;

public class PerlinNoiseGen {
    private static Random random;

    public static float[][] generatePerlinNoise(int w, int h, int octaveCount, long seed) {
        if (seed != 0l) {
            random = new Random(seed);
        } else {
            random = new Random();
        }
        float[][][] smoothNoise = new float[octaveCount][][];

        float persistance = 0.5f;
        for (int i = 0; i < octaveCount; i++) {
            smoothNoise[i] = generateSmoothNoise(generateWhiteNoise(w, h), i);
        }
        float[][] perlinNoise = new float[w][h];
        float amplitude = 1.0f;
        float totalAmplitude = 0.0f;
        for (int octave = octaveCount - 1; octave >= 0; octave--) {
            amplitude *= persistance;
            totalAmplitude += amplitude;
            for (int i = 0; i < w; i++) {
                for (int j = 0; j < h; j++) {
                    perlinNoise[i][j] += smoothNoise[octave][i][j] * amplitude;
                }
            }
        }
        for (int i = 0; i < w; i++) {
            for (int j = 0; j < h; j++) {
                perlinNoise[i][j] /= totalAmplitude;
            }
        }
        return perlinNoise;
    }

    static float[][] generateSmoothNoise(float[][] baseNoise, int octave) {
        int width = baseNoise.length;
        int height = baseNoise[0].length;

        float[][] smoothNoise = new float[width][height];

        int samplePeriod = 1 << octave;
        float sampleFrequency = 1.0f / samplePeriod;
        for (int i = 0; i < width; i++) {
            int sample_i0 = i / samplePeriod * samplePeriod;
            int sample_i1 = (sample_i0 + samplePeriod) % width;
            float horizontal_blend = (i - sample_i0) * sampleFrequency;
            for (int j = 0; j < height; j++) {
                int sample_j0 = j / samplePeriod * samplePeriod;
                int sample_j1 = (sample_j0 + samplePeriod) % height;
                float vertical_blend = (j - sample_j0) * sampleFrequency;

                float top = interpolate(baseNoise[sample_i0][sample_j0],
                    baseNoise[sample_i1][sample_j0], horizontal_blend);

                float bottom = interpolate(baseNoise[sample_i0][sample_j1],
                    baseNoise[sample_i1][sample_j1], horizontal_blend);

                smoothNoise[i][j] = interpolate(top, bottom, vertical_blend);
            }
        }
        return smoothNoise;
    }

    private static float interpolate(float x0, float x1, float alpha) {
        return x0 * (1.0f - alpha) + alpha * x1;
    }

    private static float[][] generateWhiteNoise(int width, int height) {
        float[][] noise = new float[width][height];
        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++) {
                noise[i][j] = ((float) random.nextDouble());
            }
        }
        return noise;
    }
}