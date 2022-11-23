package ru.otus;

import org.junit.platform.suite.api.SelectPackages;
import org.junit.platform.suite.api.SelectClasses;
import org.junit.platform.suite.api.Suite;

@Suite
@SelectPackages({"ru.otus.domain", "ru.otus.services"})
@SelectClasses({ru.otus.MainTest.class})

public class TestSuite {}
