public class Carro {

  String marca;
  String modelo;
  int ano;

  public Carro(String marca, String modelo, int ano) {
    this.marca = marca;
    this.modelo = modelo;
    this.ano = ano;
  }

  public exibirInformacoes() {
    System.out.println(
        String.format("Marca: %s, Modelo: %s, Ano: %i", this.marca, this.modelo, this.ano));
  }
}
