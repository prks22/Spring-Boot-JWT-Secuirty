package com.annotation;

import java.lang.annotation.Annotation;

public class AUTHORIZED  implements Annotation{

	@Override
	public Class<? extends Annotation> annotationType() {
		// TODO Auto-generated method stub
		return null;
	}

	public String[] value() {
		// TODO Auto-generated method stub
		return null;
	}

	public String[] notAllowed() {
		// TODO Auto-generated method stub
		return null;
	}



}
