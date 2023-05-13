import java.util.Scanner;
import org.json.JSONObject;
import org.supabase.SupabaseClient;
import org.supabase.SupabaseTable;

 class AddLivro {

    // Configuração Supabase:
    private static final String SUPABASE_URL = "https://fsianrdpwzapobfoweat.supabase.co";
    private static final String SUPABASE_KEY = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpc3MiOiJzdXBhYmFzZSIsInJlZiI6ImZzaWFucmRwd3phcG9iZm93ZWF0Iiwicm9sZSI6ImFub24iLCJpYXQiOjE2ODM5MzI3MzYsImV4cCI6MTk5OTUwODczNn0.HMQrYa20pz6jgjjm5UX-YNSomgBiLaSjmC9ycGXWikk";
    private static final String TABELA_LIVROS = "Livros";


    public static void main (String [] args) {

        Scanner scanner = new Scanner(System.in);

        System.out.print("Título: ");
        String titulo = scanner.nextLine();

        System.out.print("Autor: ");
        String autor = scanner.nextLine();

        System.out.print("Preco: ");
        int preco = scanner.nextInt();

        Livro livro = new Livro(titulo, autor, preco);

        JSObject livroJson = new JSONObject();
        livroJson.put("titulo", livro.getTitulo());
        livroJson.put("autor", livro.getAutor());
        livroJson.put("preco", livro.getPreco());

        SupabaseClient client = new SupabaseClient(SUPABASE_URL, SUPABASE_KEY);
        SupabaseTable tabelaLivros = client.table(TABELA_LIVROS);

        try {
            tabelaLivros.insert().row(livroJson).execute();
            System.out.println("Livro adicionado com sucesso!");
        } catch (Exception e) {
            System.out.println("Erro ao adicionar livro: " + e.getMessage());
        } finally {
            scanner.close();
        }
    }

}
public class  Livro{
    private String titulo;
    private String autor;
    private int preco;




    public Livro(String titulo, String autor, int preco) {
        this.titulo = titulo;
        this.autor = autor;
        this.preco = preco;
    }


    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getAutor() {
        return autor;
    }

    public void setAutor(String autor) {
        this.autor = autor;
    }

    public int getPreco() {
        return preco;
    }

    public void setPreco(int preco) {
        this.preco = preco;
    }
}