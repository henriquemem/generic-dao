package br.com.generic.genericDAO;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;

import br.com.generic.WeldJUnit4Runner;
import br.com.generic.dao.exception.DuplicateResultException;
import br.com.generic.entity.Fabricante;
import br.com.generic.entity.Produto;

@RunWith(WeldJUnit4Runner.class)
public class NotLikeTest extends BaseSearchTest {

	@Test
	@Override
	public void listPropertiesTest() {
		List<Long> list = produtoDao.<Long>listProperties("id")
				.notLike("embalagens.codigosBarras.codigoBarras", "7898911244759%")
				.list();
		
		assertFalse(list.isEmpty());
	}

	@Override
	@Test(expected=DuplicateResultException.class)
	public void searchPropertyTest() {
		Fabricante fabricante = fabricanteDao.searchEntity()
				.equal("codigo", 1)
				.search();
		
		assertNotNull(fabricante);
		
		produtoDao.<String>searchProperty("nome")
			.notLike("fabricante.nome", fabricante.getNome())
			.search();
	}

	@Test
	@Override
	public void listEntitiesTest() {
		List<Produto> list = produtoDao.listEntities()
				.notLike("nome", "a%")
				.list();
		
		assertFalse(list.isEmpty());
	}

	@Override
	public void searchEntityTest() {
		Produto produto = produtoDao.searchEntity()
				.notLike("embalagens.codigosBarras.codigoBarras", "7898911244759&")
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
				.notLike("nome", "a%")
				.like("fabricante.nome", fabricante.getNome())
				.list();
		
		assertFalse(list.isEmpty());
	}

	@Override
	@Test(expected=DuplicateResultException.class)
	public void searchPropertyMultTest() {
		Fabricante fabricante = fabricanteDao.searchEntity()
				.equal("codigo", 1)
				.search();
		
		assertNotNull(fabricante);
		
		produtoDao.<String>searchProperty("nome")
			.between("version", 0, 10)
			.notLike("nome", "a%")
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
				.notLike("fabricante.nome", fabricante.getNome())
				.lessThan("fabricante.codigo", 2)
				.list();
		
		assertTrue(list.isEmpty());
	}

	@Override
	@Test(expected=DuplicateResultException.class)
	public void searchEntityMultTest() {
		produtoDao.searchEntity()
			.notLike("embalagens.codigosBarras.codigoBarras", "7898911244759%")
			.greaterThanOrEqualTo("embalagens.codigosBarras.id", 0)
			.between("embalagens.codigosBarras.codigoBarras", "5898911244759", "9898911244759")
			.search();
	}

}
