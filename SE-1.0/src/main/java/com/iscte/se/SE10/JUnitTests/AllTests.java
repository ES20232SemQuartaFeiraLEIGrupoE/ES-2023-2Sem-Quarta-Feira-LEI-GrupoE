package com.iscte.se.SE10.JUnitTests;


import org.junit.platform.runner.JUnitPlatform;
import org.junit.platform.suite.api.SelectClasses;
import org.junit.runner.RunWith;


@RunWith(JUnitPlatform.class)
@SelectClasses({BlockTest.class, ConvertFilesTest.class, FileReaderWriterTest.class, MainTest.class})
public class AllTests {}

