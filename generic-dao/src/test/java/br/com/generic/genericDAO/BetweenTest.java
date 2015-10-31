package br.com.generic.genericDAO;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;

import br.com.generic.WeldJUnit4Runner;
import br.com.generic.dao.exception.DuplicateResultException;
import br.com.generic.entity.Fabricante;
import br.com.generic.entity.Produto;

@RunWith(WeldJUnit4Runner.class)
public class BetweenTest extends BaseSearchTest {

	@Test
	@Override
	public void listPropertiesTest() {
		List<Long> list = produtoDao.<Long>listProperties("id")
				.between("version", 0, 10)
				.list();
		
		assertFalse(list.isEmpty());
	}

	@Override
	@Test(expected=DuplicateResultException.class)
	public void searchPropertyTest() {
		produtoDao.<String>searchProperty("nome")
			.between("nome", "EXELON", "GINO-COLON")
			.search();
	}

	@Test
	@Override
	public void listEntitiesTest() {
		List<Produto> list = produtoDao.listEntities()
				.between("version", 0, 10)
				.list();
		
		assertFalse(list.isEmpty());
	}

	@Override
	@Test(expected=DuplicateResultException.class)
	public void searchEntityTest() {
		produtoDao.searchEntity()
			.between("nome", "EXELON", "GINO-COLON")
			.search();
	}

	@Test
	@Override
	public void listPropertiesMultTest() {
		List<Long> list = produtoDao.<Long>listProperties("id")
				.between("version", 0, 10)
				.like("nome", "a%")
				.greaterThan("embalagens.version", 1)
				.list();
		
		assertFalse(list.isEmpty());
	}

	@Override
	@Test(expected=DuplicateResultException.class)
	public void searchPropertyMultTest() {
		produtoDao.<String>searchProperty("nome")
			.between("version", 0, 10)
			.like("nome", "a%")
			.greaterThan("embalagens.version", 1)
			.search();
	}

	@Test
	@Override
	public void listEntitiesMultTest() {
		Fabricante fabricante = fabricanteDao.searchEntity()
				.equal("codigo", 1)
				.search();
		
		assertNotNull(fabricante);
		
		List<Produto> list = produtoDao.listEntities()
				.between("version", 0, 10)
				.equal("fabricante", fabricante)
				.lessThan("fabricante.codigo", 2)
				.list();
		
		assertFalse(list.isEmpty());
	}

	@Test
	@Override
	public void searchEntityMultTest() {
		produtoDao.searchEntity()
			.equal("embalagens.codigosBarras.codigoBarras", "7898911244759")
			.greaterThanOrEqualTo("embalagens.codigosBarras.id", 0)
			.between("embalagens.codigosBarras.codigoBarras", "5898911244759", "9898911244759")
			.search();
	}

	
}
