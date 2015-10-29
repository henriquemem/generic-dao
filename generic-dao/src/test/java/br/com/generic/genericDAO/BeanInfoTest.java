package br.com.generic.genericDAO;

import java.beans.BeanInfo;
import java.beans.IntrospectionException;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.util.Arrays;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;

import br.com.generic.WeldJUnit4Runner;
import br.com.generic.entity.Produto;

@RunWith(WeldJUnit4Runner.class)
public class BeanInfoTest extends BaseTest {

	@Test
	public void listPropertiesGenericOn(){
		BeanInfo beanInfo = null;
		try {
			beanInfo = Introspector.getBeanInfo(Produto.class);
		} catch (IntrospectionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		PropertyDescriptor[] propertyDescriptors=beanInfo.getPropertyDescriptors();
		
		Arrays.asList(propertyDescriptors).forEach((PropertyDescriptor p) -> System.out.println(p.getReadMethod().getName()));
	}
	
	

}
