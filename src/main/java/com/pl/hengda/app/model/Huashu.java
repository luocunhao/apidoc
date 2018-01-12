package com.pl.hengda.app.model;

import lombok.*;

/*
* 话术对象
* 不同vip_flg 即不同身份不同话术
*
* */
@AllArgsConstructor
@NoArgsConstructor
@RequiredArgsConstructor
@ToString
public class Huashu {
    @Getter @Setter private String id;
    @Getter @Setter @NonNull private String vip_flag;
    @Getter @Setter @NonNull private String huashu;
}
