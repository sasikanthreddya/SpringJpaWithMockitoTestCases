@Test
	public void testPrivateMethod() throws Exception {
		// When
		int ammount=2000;
		OrderService spy = PowerMockito.mock(OrderService.class);
		doReturn(ammount).when(spy, "addDiscount", ArgumentMatchers.any());
		PowerMockito.when(spy.checkoutOrder(request)).thenCallRealMethod();
		spy.checkoutOrder(request);
		Mockito.verify(spy, Mockito.times(3)).checkoutOrder(request);
    
    //ref https://github.com/Java-Techie-jt/spring-boot-powermock
