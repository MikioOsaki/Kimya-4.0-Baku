package com.example.mikio.kimya_3_0_Yevlakh;

public class DataCompound {

    public final String stoffname;
    public final String casnr;
    public final String egnr;
    public final int id;
    public final String url;

    public DataCompound(String stoffname, String casnr, String egnr, int id, String url) {
        this.stoffname = stoffname;
        this.casnr = casnr;
        this.egnr = egnr;
        this.id = id;
        this.url = url;
    }
}