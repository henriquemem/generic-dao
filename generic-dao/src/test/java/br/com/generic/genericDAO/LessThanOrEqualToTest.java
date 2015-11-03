package br.com.generic.genericDAO;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;

import br.com.generic.WeldJUnit4Runner;
import br.com.generic.dao.exception.DuplicateResultException;
import br.com.generic.entity.Fabricante;
import br.com.generic.entity.Produto;

@RunWith(WeldJUnit4Runner.class)
public class LessThanOrEqualToTest extends BaseSearchTest {

	@Test
	@Override
	public void listPropertiesTest() {
		List<Long> list = produtoDao.<Long>listProperties("id")
				.lessThanOrEqualTo("embalagens.id", 999999999)
				.list();
		
		assertFalse(list.isEmpty());
	}

	@Override
	@Test(expected=DuplicateResultException.class)
	public void searchPropertyTest() {
		Fabricante fabricante = fabricanteDao.searchEntity()
				.lessThanOrEqualTo("codigo", 1000)
				.search();
		
		assertNotNull(fabricante);
		
		produtoDao.<String>searchProperty("nome")
			.equal("fabricante", fabricante)
			.search();
	}

	@Test
	@Override
	public void listEntitiesTest() {
		List<Produto> list = produtoDao.listEntities()
				.lessThanOrEqualTo("codigo", 10000)
				.list();
		
		assertFalse(list.isEmpty());
	}

	@Override
	public void searchEntityTest() {
		Produto produto = produtoDao.searchEntity()
				.lessThanOrEqualTo("codigo", 10000)
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
				.lessThanOrEqualTo("codigo", 10000)
				.equal("fabricante", fabricante)
				.list();
		
		assertFalse(list.isEmpty());
	}

	@Override
	@Test(expected=DuplicateResultException.class)
	public void searchPropertyMultTest() {
		Fabricante fabricante = fabricanteDao.searchEntity()
				.lessThanOrEqualTo("codigo", 10000)
				.search();
		
		assertNotNull(fabricante);
		
		produtoDao.<String>searchProperty("nome")
			.between("version", 0, 10)
			.like("nome", "a%")
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
				.lessThanOrEqualTo("fabricante.codigo", 1000)
				.list();
		
		assertFalse(list.isEmpty());
	}

	@Test
	@Override
	public void searchEntityMultTest() {
		produtoDao.searchEntity()
			.equal("embalagens.codigosBarras.codigoBarras", "7898911244759")
			.lessThanOrEqualTo("embalagens.codigosBarras.id", 999999999)
			.between("embalagens.codigosBarras.codigoBarras", "5898911244759", "9898911244759")
			.search();
	}

}
