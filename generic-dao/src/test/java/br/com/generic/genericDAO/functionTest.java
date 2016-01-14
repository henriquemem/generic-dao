package br.com.generic.genericDAO;

import br.com.generic.WeldJUnit4Runner;
import br.com.generic.entity.Embalagem;
import br.com.generic.entity.EmbalagemCodigoBarras;
import br.com.generic.entity.Produto;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.List;

@RunWith(WeldJUnit4Runner.class)
public class FunctionTest extends BaseListTest {

	@Test
	public void max() {

		Long idMax = produtoDao.max("fabricante.id");
		System.out.print("max: " + idMax);

	}

	@Test
	public void avg() {

		double idMax = produtoDao.avg("fabricante.id");
		System.out.print("avg: " + idMax);

	}

	@Test
	public void min() {

		Long idMax = produtoDao.min("fabricante.id");
		System.out.print("min: " + idMax);

	}

	@Test
	public void count() {

		Long idMax = produtoDao.count("fabricante.id");
		System.out.print("count: " + idMax);

	}

	@Test
	public void sum() {

		Long idMax = produtoDao.sum("fabricante.id");
		System.out.print("sum: " + idMax);

	}




	
}
