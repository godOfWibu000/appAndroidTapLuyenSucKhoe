package com.example.a2millionfitness;

public class KeHoach {
    private int maKeHoach;

    private String tenKeHoach, ghiChu, anh;

    public KeHoach(int maKeHoach, String tenKeHoach, String ghiChu, String anh) {
        this.maKeHoach = maKeHoach;
        this.tenKeHoach = tenKeHoach;
        this.ghiChu = ghiChu;
        this.anh = anh;
    }

    public int getMaKeHoach() {
        return maKeHoach;
    }

    public String getTenKeHoach() {
        return tenKeHoach;
    }

    public String getGhiChu() {
        return ghiChu;
    }

    public String getAnh() {
        return anh;
    }

    public void setMaKeHoach(int maKeHoach) {
        this.maKeHoach = maKeHoach;
    }

    public void setTenKeHoach(String tenKeHoach) {
        this.tenKeHoach = tenKeHoach;
    }

    public void setGhiChu(String ghiChu) {
        this.ghiChu = ghiChu;
    }

    public void setAnh(String anh) {
        this.anh = anh;
    }
}
