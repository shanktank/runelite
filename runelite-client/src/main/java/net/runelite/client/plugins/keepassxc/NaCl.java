package net.runelite.client.plugins.keepassxc;

import net.runelite.client.plugins.keepassxc.crypto.*;

import java.util.Formatter;

public class NaCl {
    static final int crypto_secretbox_KEYBYTES = 32;
    static final int crypto_secretbox_NONCEBYTES = 24;
    static final int crypto_secretbox_ZEROBYTES = 32;
    static final int crypto_secretbox_BOXZEROBYTES = 16;
    static final int crypto_secretbox_BEFORENMBYTES = 32;
    private byte[] precomputed;

    public NaCl(byte[] privatekey, byte[] publickey) throws Exception {
        this.precomputed = new byte[32];
        if (privatekey.length < 32) {
            throw new Exception("Private key too short");
        } else if (publickey.length < 32) {
            throw new Exception("Public key too short");
        } else {
            curve25519xsalsa20poly1305.crypto_box_beforenm(this.precomputed, publickey, privatekey);
        }
    }

    public NaCl(String privatekey, String publickey) throws Exception {
        this(getBinary(privatekey), getBinary(publickey));
    }

    public byte[] encrypt(byte[] input, byte[] nonce) {
        byte[] paddedinput = new byte[input.length + 32];
        byte[] output = new byte[input.length + 32];
        System.arraycopy(input, 0, paddedinput, 32, input.length);
        curve25519xsalsa20poly1305.crypto_box_afternm(output, paddedinput, (long)paddedinput.length, nonce, this.precomputed);
        return output;
    }

    public byte[] encrypt(byte[] input, int inputlength, byte[] nonce) {
        byte[] paddedinput = new byte[inputlength + 32];
        byte[] output = new byte[inputlength + 32];
        System.arraycopy(input, 0, paddedinput, 32, inputlength);
        curve25519xsalsa20poly1305.crypto_box_afternm(output, paddedinput, (long)paddedinput.length, nonce, this.precomputed);
        return output;
    }

    public byte[] decrypt(byte[] input, byte[] nonce) {
        byte[] paddedoutput = new byte[input.length];
        byte[] output = new byte[input.length - 32];
        curve25519xsalsa20poly1305.crypto_box_afternm(paddedoutput, input, (long)input.length, nonce, this.precomputed);
        System.arraycopy(paddedoutput, 32, output, 0, paddedoutput.length - 32);
        return output;
    }

    public byte[] decrypt(byte[] input, int inputlength, byte[] nonce) {
        byte[] paddedoutput = new byte[inputlength];
        byte[] output = new byte[inputlength - 32];
        curve25519xsalsa20poly1305.crypto_box_afternm(paddedoutput, input, (long)inputlength, nonce, this.precomputed);
        System.arraycopy(paddedoutput, 32, output, 0, paddedoutput.length - 32);
        return output;
    }

    public static byte[] getBinary(String s) {
        int len = s.length();
        byte[] data = new byte[len / 2];

        for(int i = 0; i < len; i += 2) {
            data[i / 2] = (byte)((Character.digit(s.charAt(i), 16) << 4) + Character.digit(s.charAt(i + 1), 16));
        }

        return data;
    }

    public static String asHex(byte[] buf) {
        Formatter formatter = new Formatter();
        byte[] var2 = buf;
        int var3 = buf.length;

        for(int var4 = 0; var4 < var3; ++var4) {
            byte b = var2[var4];
            formatter.format("%02x", b);
        }

        return formatter.toString();
    }

    public static String asHex(int[] buf) {
        Formatter formatter = new Formatter();
        int[] var2 = buf;
        int var3 = buf.length;

        for(int var4 = 0; var4 < var3; ++var4) {
            int b = var2[var4];
            formatter.format("%02x", b);
        }

        return formatter.toString();
    }
}