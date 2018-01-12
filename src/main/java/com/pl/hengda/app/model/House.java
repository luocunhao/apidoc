package com.pl.hengda.app.model;

import lombok.*;

@AllArgsConstructor
@RequiredArgsConstructor
@ToString
public class House {
    @Getter @Setter  private String id;
    @Getter @Setter  @NonNull  private String housename;
    @Getter @Setter @NonNull private String housedirect;
    @Getter @Setter @NonNull private String apartmentlayout;
    @Getter @Setter @NonNull private String measure;
    @Getter @Setter @NonNull private String referenceprice;
    @Getter @Setter @NonNull private String floor;
    @Getter @Setter @NonNull private String roomnumber;
    @Getter @Setter  @NonNull private String desc;
    @Getter @Setter @NonNull private String housemappath;
    @Getter @Setter @NonNull private String discount;
}
