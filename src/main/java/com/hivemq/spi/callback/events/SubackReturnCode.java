package com.hivemq.spi.callback.events;

/**
 * @author Georg Held
 */
public enum SubackReturnCode {
    SUCCESS_QOS_0((byte) 0x00),
    SUCCESS_QOS_1((byte) 0x01),
    SUCCESS_QOS_2((byte) 0x02),
    FAILURE((byte) 0x80);

    private final byte code;

    SubackReturnCode(final byte code) {
        this.code = code;
    }

    public byte getCode() {
        return code;
    }

    public static SubackReturnCode valueOf(final byte code) {

        for (final SubackReturnCode subackReturnCode : SubackReturnCode.values()) {
            if (subackReturnCode.getCode() == code) {
                return subackReturnCode;
            }
        }
        throw new IllegalArgumentException("Unknown SUBACK return code: " + code);
    }
}
