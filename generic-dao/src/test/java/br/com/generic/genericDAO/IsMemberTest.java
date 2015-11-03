package br.com.generic.genericDAO;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;

import br.com.generic.WeldJUnit4Runner;
import br.com.generic.dao.exception.DuplicateResultException;
import br.com.generic.entity.Embalagem;
import br.com.generic.entity.EmbalagemCodigoBarras;
import br.com.generic.entity.Produto;

@RunWith(WeldJUnit4Runner.class)
public class IsMemberTest extends BaseSearchTest {

	@Test
	@Override
	public void listPropertiesTest() {

		Produto produto = produtoDao.searchEntity()
				.equal("codigo", 200)
				.search();
		
		Embalagem embalagem = produto.getEmbalagens().iterator().next();
		
		List<Long> list = produtoDao.<Long>listProperties("id")
				.isMember("embalagens", embalagem)
				.list();
		
		assertFalse(list.isEmpty());
		
		EmbalagemCodigoBarras codigoBarras = embalagem.getCodigosBarras().iterator().next();
		
		list = produtoDao.<Long>listProperties("id")
				.isMember("embalagens.codigosBarras", codigoBarras)
				.list();
		
		assertEquals(1, list.size());
	}

	@Override
	@Test
	public void searchPropertyTest() {
		
		Produto produto = produtoDao.searchEntity()
				.equal("codigo", 200)
				.search();
		
		Embalagem embalagem = produto.getEmbalagens().iterator().next();
		
		produtoDao.<Long>searchProperty("id")
				.isMember("embalagens", embalagem)
				.search();
	}

	@Test
	@Override
	public void listEntitiesTest() {
		Produto produto = produtoDao.searchEntity()
				.equal("codigo", 200)
				.search();
		
		Embalagem embalagem = produto.getEmbalagens().iterator().next();
		
		EmbalagemCodigoBarras codigoBarras = embalagem.getCodigosBarras().iterator().next();
		
		List<Produto> list = produtoDao.listEntities()
				.isMember("embalagens.codigosBarras", codigoBarras)
				.list();
		
		assertEquals(1, list.size());
	}

	@Override
	@Test
	public void searchEntityTest() {
		
		Produto produto = produtoDao.searchEntity()
				.equal("codigo", 200)
				.search();
		
		Embalagem embalagem = produto.getEmbalagens().iterator().next();
		
		EmbalagemCodigoBarras codigoBarras = embalagem.getCodigosBarras().iterator().next();
		produto = produtoDao.searchEntity()
				.isMember("embalagens.codigosBarras", codigoBarras)
				.search();
		assertNotNull(produto);
	}

	@Test
	@Override
	public void listPropertiesMultTest() {
		Produto produto = produtoDao.searchEntity()
				.equal("codigo", 200)
				.search();
		
		Embalagem embalagem = produto.getEmbalagens().iterator().next();
		
		EmbalagemCodigoBarras codigoBarras = embalagem.getCodigosBarras().iterator().next();
		
		
		List<Long> list = produtoDao.<Long>listProperties("id")
				.between("version", 10, 20)
				.isMember("embalagens.codigosBarras", codigoBarras)
				.list();
		
		assertEquals(1, list.size());
	}

	@Override
	@Test
	public void searchPropertyMultTest() {
		Produto produto = produtoDao.searchEntity()
				.equal("codigo", 200)
				.search();
		
		Embalagem embalagem = produto.getEmbalagens().iterator().next();
		
		EmbalagemCodigoBarras codigoBarras = embalagem.getCodigosBarras().iterator().next();
		
		
		produtoDao.<String>searchProperty("nome")
			.between("version", 10, 20)
			.isMember("embalagens.codigosBarras", codigoBarras)
			.search();
	}

	@Test
	@Override
	public void listEntitiesMultTest() {
		Produto produto = produtoDao.searchEntity()
				.equal("codigo", 200)
				.search();
		
		Embalagem embalagem = produto.getEmbalagens().iterator().next();
		
		EmbalagemCodigoBarras codigoBarras = embalagem.getCodigosBarras().iterator().next();
		
		List<Produto> list = produtoDao.listEntities()
				.between("version", 10, 20)
				.isMember("embalagens.codigosBarras", codigoBarras)
				.list();
		
		assertEquals(1, list.size());
	}

	@Test
	@Override
	public void searchEntityMultTest() {
		Produto produto = produtoDao.searchEntity()
				.equal("codigo", 200)
				.search();
		
		Embalagem embalagem = produto.getEmbalagens().iterator().next();
		
		EmbalagemCodigoBarras codigoBarras = embalagem.getCodigosBarras().iterator().next();
		produto = produtoDao.searchEntity()
				.isMember("embalagens.codigosBarras", codigoBarras)
				.between("embalagens.version", 10, 20)
				.search();
		
		assertNotNull(produto);
	}
	
}
