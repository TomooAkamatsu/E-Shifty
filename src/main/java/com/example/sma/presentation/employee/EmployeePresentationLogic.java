package com.example.sma.presentation.employee;

final class EmployeePresentationLogic {

    static final String ifWorkingFormGetId(final String key, final String value) {
        if (!key.equals("workingFormName")) return value;
        return getWorkingFormIdFromName(value);
    }

    static final String getWorkingFormIdFromName(final String workingFormName) {

//        もっといいやり方があるとは思うが...
        String workingFormId = "0";
        if (workingFormName.equals("正社員")) workingFormId = "1";
        if (workingFormName.equals("正社員(時短)")) workingFormId = "2";
        if (workingFormName.equals("パート")) workingFormId = "3";
        if (workingFormId.equals("0")) throw new RuntimeException();

        return workingFormId;
    }

    static final String camelToSnake(final String camel) {
        if (camel.isEmpty()) {
            return camel;
        }
        final StringBuilder sb = new StringBuilder(camel.length() + camel.length());
        for (int i = 0; i < camel.length(); i++) {
            final char c = camel.charAt(i);
            if (Character.isUpperCase(c)) {
                sb.append(sb.length() != 0 ? '_' : "").append(Character.toLowerCase(c));
            } else {
                sb.append(Character.toLowerCase(c));
            }
        }
        return sb.toString();
    }

    static String checkWorkingForm(String key) {
        if (key.equals("workingFormName")) return "workingFormId";
        return key;
    }
}
