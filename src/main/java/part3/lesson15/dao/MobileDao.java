package part3.lesson15.dao;


import  part3.lesson15.pojo.Mobile;

import java.util.Collection;

public interface MobileDao {
  boolean addMobile(Mobile mobile);

  Mobile getMobileById(Integer id);

  boolean updateMobileById(Mobile mobile);

  boolean deleteMobileById(Integer id);

  void createTable();

  Collection<Mobile> getAllMobile();
}
