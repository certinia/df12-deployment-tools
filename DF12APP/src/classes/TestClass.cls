@isTest
private class TestClass {

  @isTest 
  private static void test1()
  {
    CustomSetting1__c cs = new CustomSetting1__c();
    cs.name = 'test1';
    cs.Field1__c = 'test1';
    insert cs;

	Account a = new Account();
	a.name = 'account1';
	insert a;
	
	a.name = 'account1.1';
	update a; 
  
    Class1 c1 = new Class1();
    Class2 c2 = new Class2();
    Class3 c3 = new Class3();
    
    cs.Field1__c = 'test2';
    update cs;
  
    System.assert(false,true);
  
  } 

 @isTest 
  private static void test2()
  {
    System.assert(false,true);
  } 

 @isTest 
  private static void test3()
  {
    System.assert(false,true);
  } 


}