package com.example.ramkumar.studentalumini;

/**
 * Created by Ram Kumar on 1/20/2018.
 */
public class RowItemSelect {

    private String studentIdStr;
    private String studentNameStr;
    private String studentAgeStr;
    private String gender;
    private String departmentStr;
    private String batchStr;
    private String indexKey;
    //Search class variables
    private String sStudentIdStr;
    private String sStudentNameStr;
    private String sStudentAgeStr;
    private String sGender;
    private String sDepartmentStr;
    private String sBatchStr;
    private String sIndexKey;
    private String searchDummy;

    /*  This constructor is for Registration class  */
    public RowItemSelect(String studentIdStr, String studentNameStr, String studentAgeStr, String gender, String departmentStr, String batchStr) {

        this.studentIdStr = studentIdStr;
        this.studentNameStr = studentNameStr;
        this.studentAgeStr = studentAgeStr;
        this.gender = gender;
        this.departmentStr = departmentStr;
        this.batchStr = batchStr;
    }

    /*  This constructor is for Delete Class    */
    public RowItemSelect(String studentIdStr, String studentNameStr, String studentAgeStr, String gender, String departmentStr, String batchStr, String indexKey) {

        this.studentIdStr = studentIdStr;
        this.studentNameStr = studentNameStr;
        this.studentAgeStr = studentAgeStr;
        this.gender = gender;
        this.departmentStr = departmentStr;
        this.batchStr = batchStr;
        this.indexKey = indexKey;

    }

    /*  This constructor is for Search Class    */
    public RowItemSelect(String sStudentIdStr, String sStudentNameStr, String sStudentAgeStr, String sGender, String sDepartmentStr, String sBatchStr, String sIndexKey, String searchDummy){

        this.sStudentIdStr = sStudentIdStr;
        this.sStudentNameStr = sStudentNameStr;
        this.sStudentAgeStr = sStudentAgeStr;
        this.sGender = sGender;
        this.sDepartmentStr = sDepartmentStr;
        this.sBatchStr = sBatchStr;
        this.sIndexKey = sIndexKey;

    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getStudentIdStr() {
        return studentIdStr;
    }

    public void setStudentIdStr(String studentIdStr) {
        this.studentIdStr = studentIdStr;
    }

    public String getStudentNameStr() {
        return studentNameStr;
    }

    public void setStudentNameStr(String studentNameStr) {
        this.studentNameStr = studentNameStr;
    }

    public String getStudentAgeStr() {
        return studentAgeStr;
    }

    public void setStudentAgeStr(String studentAgeStr) {
        this.studentAgeStr = studentAgeStr;
    }

    public String getDepartmentStr() {
        return departmentStr;
    }

    public void setDepartmentStr(String departmentStr) {
        this.departmentStr = departmentStr;
    }

    public String getBatchStr() {
        return batchStr;
    }

    public void setBatchStr(String batchStr) {
        this.batchStr = batchStr;
    }

    public String getIndexKey() {
        return indexKey;
    }

    public void setIndexKey(String indexKey) {
        this.indexKey = indexKey;
    }

    public String getsStudentIdStr() {
        return sStudentIdStr;
    }

    public void setsStudentIdStr(String sStudentIdStr) {
        this.sStudentIdStr = sStudentIdStr;
    }

    public String getsStudentNameStr() {
        return sStudentNameStr;
    }

    public void setsStudentNameStr(String sStudentNameStr) {
        this.sStudentNameStr = sStudentNameStr;
    }

    public String getsStudentAgeStr() {
        return sStudentAgeStr;
    }

    public void setsStudentAgeStr(String sStudentAgeStr) {
        this.sStudentAgeStr = sStudentAgeStr;
    }

    public String getsGender() {
        return sGender;
    }

    public void setsGender(String sGender) {
        this.sGender = sGender;
    }

    public String getsDepartmentStr() {
        return sDepartmentStr;
    }

    public void setsDepartmentStr(String sDepartmentStr) {
        this.sDepartmentStr = sDepartmentStr;
    }

    public String getsBatchStr() {
        return sBatchStr;
    }

    public void setsBatchStr(String sBatchStr) {
        this.sBatchStr = sBatchStr;
    }

    public String getsIndexKey() {
        return sIndexKey;
    }

    public void setsIndexKey(String sIndexKey) {
        this.sIndexKey = sIndexKey;
    }
}
