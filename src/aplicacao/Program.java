package aplicacao;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import dominio.Pessoa;

public class Program {

	public static void main(String[] args) {
		
		/*
		 * TESTE MINIMO
		 * SIMPLES
		 * */

		Pessoa p1 = new Pessoa(null, "Carlos da Silva", "carlos@gmail.com");
		Pessoa p2 = new Pessoa(null, "Joaozinho", "joaozinho@gmail.com");
		Pessoa p3 = new Pessoa(null, "Maria", "maria@gmail.com");
		
		//conexão com db
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("exemplo-jpa");
		EntityManager em = emf.createEntityManager();
		
		//salvar dados no db
		em.getTransaction().begin(); //iniciar uma transacao com db, interecao
		em.persist(p1);
		em.persist(p2);
		em.persist(p3);
		em.getTransaction().commit(); //confirmar as auteracoes
		
		System.out.println("Pronto");
		System.out.println("Buscando...");
		
		Pessoa p = em.find(Pessoa.class, 2);//buscar no sb
		System.out.println(p);
		
		//removendo
		//OBS: TODA VEZ QUE FOR MEXER NO DB PRECISA INICIAR O TRANSACTION.BEGIN E TRANSACTION.COMMIT
		Pessoa premove = em.find(Pessoa.class, 2);
		em.getTransaction().begin();
		em.remove(p);
		em.getTransaction().commit();
		
		System.out.println("Pronto!");
		em.close(); //fechar conexao
		emf.close();
	}

}
