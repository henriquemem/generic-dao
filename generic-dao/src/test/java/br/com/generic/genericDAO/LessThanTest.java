package br.com.generic.genericDAO;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;

import br.com.generic.WeldJUnit4Runner;
import br.com.generic.dao.exception.DuplicateResultException;
import br.com.generic.entity.Fabricante;
import br.com.generic.entity.Produto;

@RunWith(WeldJUnit4Runner.class)
public class LessThanTest extends BaseSearchTest {

	@Test
	@Override
	public void listPropertiesTest() {
		List<Long> list = produtoDao.<Long>listProperties("id")
				.lessThan("embalagens.id", 999999999)
				.list();
		
		assertFalse(list.isEmpty());
	}

	@Override
	@Test(expected=DuplicateResultException.class)
	public void searchPropertyTest() {
		Fabricante fabricante = fabricanteDao.searchEntity()
				.lessThan("codigo", 9999)
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
				.lessThan("codigo", 99999)
				.list();
		
		assertFalse(list.isEmpty());
	}

	@Override
	public void searchEntityTest() {
		Produto produto = produtoDao.searchEntity()
				.lessThan("codigo", 99999)
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
				.lessThan("codigo", 9999)
				.equal("fabricante", fabricante)
				.list();
		
		assertFalse(list.isEmpty());
	}

	@Override
	@Test(expected=DuplicateResultException.class)
	public void searchPropertyMultTest() {
		Fabricante fabricante = fabricanteDao.searchEntity()
				.lessThan("codigo", 99999)
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
				.lessThan("fabricante.codigo", 999999)
				.list();
		
		assertFalse(list.isEmpty());
	}

	@Test
	@Override
	public void searchEntityMultTest() {
		produtoDao.searchEntity()
			.equal("embalagens.codigosBarras.codigoBarras", "7898911244759")
			.lessThan("embalagens.codigosBarras.id", 99999999)
			.between("embalagens.codigosBarras.codigoBarras", "5898911244759", "9898911244759")
			.search();
	}
}
