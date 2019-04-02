package com.davita.dps.patientschedule.model;

public enum PatientScheduleMessageType {

    // Messages sent from Patient to MT
    BOOKING,
    ADD_TO_WAITLIST,
    CANCELLATION,
    WAITLISTED_APPT_ACCEPTED,
    WAITLISTED_APPT_DECLINED,

    // Messages sent from MT to Patient
    BOOKING_CONFIRMATION,
    CANCELLATION_CONFIRMATION,
    WAITLIST_CONFIRMATION,
    REMINDER,
    WAITLIST;

    /** get the enum value for the given String */
    public static PatientScheduleMessageType getTypeEnum(String messageType) {
        for (PatientScheduleMessageType psmt : PatientScheduleMessageType.values()) {
            if (psmt.toString().equalsIgnoreCase(messageType)) {
                return psmt;
            }
        }
        return null;
    }
}
