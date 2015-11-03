package br.com.generic.genericDAO;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;

import br.com.generic.WeldJUnit4Runner;
import br.com.generic.dao.exception.DuplicateResultException;
import br.com.generic.entity.Fabricante;
import br.com.generic.entity.Produto;

@RunWith(WeldJUnit4Runner.class)
public class IsNullTest extends BaseSearchTest {

	@Test
	@Override
	public void listPropertiesTest() {
		List<Long> list = produtoDao.<Long>listProperties("id")
				.isNull("embalagens.codigoMs")
				.list();
		
		assertFalse(list.isEmpty());
	}

	@Override
	@Test
	public void searchPropertyTest() {
		produtoDao.<String>searchProperty("nome")
			.isNull("fabricante")
			.search();
	}

	@Test
	@Override
	public void listEntitiesTest() {
		List<Produto> list = produtoDao.listEntities()
				.isNull("codigo")
				.list();
		
		assertTrue(list.isEmpty());
	}

	@Override
	public void searchEntityTest() {
		Produto produto = produtoDao.searchEntity()
				.isNull("codigo")
				.search();
		
		assertNotNull(produto);
	}

	@Test
	@Override
	public void listPropertiesMultTest() {
		
		Fabricante fabricante = fabricanteDao.searchEntity()
				.equal("codigo", 1)
				.search();
		
		assertNotNull(fabricante);
		
		
		List<Long> list = produtoDao.<Long>listProperties("id")
				.between("version", 0, 10)
				.isNull("nome")
				.equal("fabricante", fabricante)
				.list();
		
		assertTrue(list.isEmpty());
	}

	@Override
	@Test
	public void searchPropertyMultTest() {
		Fabricante fabricante = fabricanteDao.searchEntity()
				.equal("codigo", 1)
				.search();
		
		assertNotNull(fabricante);
		
		produtoDao.<String>searchProperty("nome")
			.between("version", 0, 10)
			.isNull("nome")
			.equal("fabricante", fabricante)
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
				.isNull("fabricante.codigo")
				.list();
		
		assertTrue(list.isEmpty());
	}

	@Test(expected=DuplicateResultException.class)
	@Override
	public void searchEntityMultTest() {
		produtoDao.searchEntity()
			.isNull("embalagens.codigoMs")
			.greaterThanOrEqualTo("embalagens.codigosBarras.id", 0)
			.between("embalagens.codigosBarras.codigoBarras", "5898911244759", "9898911244759")
			.search();
	}
}
