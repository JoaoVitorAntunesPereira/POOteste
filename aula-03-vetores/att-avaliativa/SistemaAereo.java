import java.io.BufferedReader;
import java.io.InputStreamReader;

public class SistemaAereo {
    private Companhia c1;
    BufferedReader reader;

    public static void main(String[] args) throws Exception {
        SistemaAereo sa = new SistemaAereo();
        sa.c1 = new Companhia();
        sa.reader = new BufferedReader(new InputStreamReader(System.in));

        System.out.println("Informe o nome da companhia: ");
        sa.c1.setNome(sa.reader.readLine());
        System.out.println("Informe a sede da companhia: ");
        sa.c1.setSede(sa.reader.readLine());

        sa.menu();
    }

    private void menu() throws Exception {
        String opcao = "0";

        while (!opcao.equals("4")) {
            System.out.println("----------------------------------------------");
            System.out.println("[1] Cadastrar novo voo: ");
            System.out.println("[2] Listar voos existentes: ");
            System.out.println("[3] Consultar um voo: ");
            System.out.println("[4] Sair");
            opcao = this.reader.readLine();

            switch (opcao) {
                case "1":
                    cadastrarVoo();
                    break;
                case "2":
                    listarVoos();
                    break;
                case "3":
                    consultarVoo();
                    break;
                case "4":
                    break;
                default:
                    System.out.println("Informe uma opção válida!");
                    break;
            }
        }
    }

    private void cadastrarVoo() throws Exception {
        Voo v = new Voo();

        System.out.println("CADASTRO DO VOO");
        System.out.println("Informe o ID do voo");
        v.setIdVoo(this.reader.readLine());
        System.out.println("Informe o destino: ");
        v.setDestino(this.reader.readLine());
        System.out.println("Informe a origem: ");
        v.setOrigem(this.reader.readLine());
        System.out.println("Informe a data do voo: ");
        v.setData(this.reader.readLine());
        System.out.println("Informe a hora de saída do voo");
        v.setHoraSaida(this.reader.readLine());
        System.out.println("-----CADASTRO DE PASSAGEIROS-----");

        for (int i = 0; i < 50; i++) {
            System.out.println("Informe o nome do passageiro: ");
            String nome = this.reader.readLine();
            
            if (nome.equals("")) {
                break;
            }
            
            Passageiro passageiro = new Passageiro();
            passageiro.setNome(nome);
            System.out.println("Informe a identificação do passageiro |Passaporte/CPF| :");
            passageiro.setIdPass(this.reader.readLine());

            boolean confirm = false;
            while (!confirm) {
                // Exibir os assentos disponíveis
                String[] assentos = Passageiro.getAssentos();
                System.out.println("Assentos disponíveis:");
                int cont = 0;
                for (String assento : assentos) {
                    if (cont == 0) System.out.print("\nEconômica: ");
                    else if (cont == 25) System.out.print("\nExecutiva: ");
                    else if (cont == 40) System.out.print("\nPrimeira Classe: ");
                    System.out.print("[" + assento + "] ");
                    cont++;
                }

                System.out.println("\nEscolha um dos assentos:");
                String ass = this.reader.readLine();

                // Confirmar o assento
                confirm = confirmarAssento(ass, passageiro);
                if (confirm) {
                    System.out.println("Assento " + ass + " confirmado para " + nome);
                } else {
                    System.out.println("Assento inválido ou já ocupado. Escolha outro.");
                }
            }
        }
    }

    private void listarVoos() throws Exception {
        // Implementação da listagem de voos
    }

    private void consultarVoo() throws Exception {
        // Implementação da consulta de voos
    }

    private Boolean confirmarAssento(String ass, Passageiro p) {
        String[] vetAss = Passageiro.getAssentos();

        for (int i = 0; i < vetAss.length; i++) {
            if (vetAss[i].equalsIgnoreCase(ass) && !vetAss[i].equals("XX")) {
                p.setNumAssento(vetAss[i]);
                Passageiro.setAssento(i, "XX"); // Marca o assento como ocupado
                return true;
            }
        }
        return false;
    }
}