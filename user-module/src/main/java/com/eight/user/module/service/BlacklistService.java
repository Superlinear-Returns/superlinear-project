package com.eight.user.module.service;

public interface BlacklistService {

    void checkBlacklist(String ipAddress);

    void addBlacklist(String ipAddress, String remark);

}
