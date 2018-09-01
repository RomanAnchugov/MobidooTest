package modidootest.romananchugov.ru.mobidootest.network;

import com.google.gson.annotations.SerializedName;

public class ApiCheckRequest {
    @SerializedName("package")
    String pack;
    String simOperator;
    String simCountry;
    String networkOperator;
    String locale;
    String deviceManufacturer;
    String deviceModel;
    String additionalData;

    public ApiCheckRequest(String pack, String simOperator, String simCountry, String networkOperator, String locale, String deviceManufacturer, String deviceModel, String additionalData) {
        this.pack = pack;
        this.simOperator = simOperator;
        this.simCountry = simCountry;
        this.networkOperator = networkOperator;
        this.locale = locale;
        this.deviceManufacturer = deviceManufacturer;
        this.deviceModel = deviceModel;
        this.additionalData = additionalData;
    }

    public ApiCheckRequest() {
        this.pack = "ru.romananchugov.mobidootest";
        this.simOperator = "MTS RUS";
        this.simCountry = "ru";
        this.networkOperator = "MTS RUS";
        this.locale = "ru_RU";
        this.deviceManufacturer = "LGE";
        this.deviceModel = "Nexus 4";
        this.additionalData = "some string";
    }
}
