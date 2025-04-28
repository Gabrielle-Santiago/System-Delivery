async function mostrarProduto() {
  try {
    const resposta = await fetch('http://localhost:8080/admin/home');
    if (!resposta.ok) {
      throw new Error('Erro na requisição');
    }
    const dados = await resposta.json();

    document.getElementById('resultado').innerText = JSON.stringify(dados, null, 2);
  } catch (erro) {
    console.error('Erro:', erro);
    document.getElementById('resultado').innerText = 'Erro ao buscar os dados.';
  }
}
