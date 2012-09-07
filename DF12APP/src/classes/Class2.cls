public with sharing class Class2 {


  public Object2__c o = new Object2__c();

  public Class2()
  {
    o.Field1__c = 'field1';
    insert o;
  }

}