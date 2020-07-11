package com.fictional.shop;

import org.junit.platform.runner.JUnitPlatform;
import org.junit.platform.suite.api.SelectClasses;
import org.junit.runner.RunWith;

import com.fictional.shop.controller.ContactsManagementControllerIT;
@RunWith(JUnitPlatform.class)
//@SelectPackages({"com.howtodoinjava.junit5.examples.packageA","com.howtodoinjava.junit5.examples.packageB"}) 
@SelectClasses({DatastoreSystemsHealthTest.class, ContactsManagementControllerIT.class})
public class ContinuousIntegrationTestSuite 
{
}