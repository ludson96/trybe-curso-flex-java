public class Carro {

  private String marca;
  private int ano;
  private String modelo;
  public static int contador = 0;

  public Carro(String marca, String modelo, int ano) {
    this.marca = marca;
    this.modelo = modelo;
    this.ano = ano;

    contador++;
  }

  public void exibirInformacoes() {
    System.out.printf("Marca: %s, Modelo: %s, Ano: %d%n", this.marca, this.modelo, this.ano);
  }

  public static void quantidadeDeCarros() {
    System.out.printf("Total de carros: %d", contador);
  }

  public String getMarca() {
    return marca;
  }

  public void setMarca(String marca) {
    this.marca = marca;
  }

  public int getAno() {
    return ano;
  }

  public void setAno(int ano) {
    this.ano = ano;
  }

  public String getModelo() {
    return modelo;
  }

  public void setModelo(String modelo) {
    this.modelo = modelo;
  }
}
