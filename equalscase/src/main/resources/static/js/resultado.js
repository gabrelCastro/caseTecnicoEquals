function aplicarFiltros() {
    const dataInicio = document.getElementById('dataInicio').value;
    const dataFim = document.getElementById('dataFim').value;
    const instituicaoSelecionada = document.getElementById('filtroInstituicao').value.trim().toLowerCase();

    const linhas = document.querySelectorAll('.tabela-detalhes tbody tr');

    linhas.forEach(linha => {
        const dataTransacaoStr = linha.children[0].innerText.trim(); // data da linha
        const instituicao = linha.children[11].innerText.trim().toLowerCase();

        const dataTransacao = new Date(dataTransacaoStr);

        let dataOk = true;
        if (dataInicio) {
            const dataInicioObj = new Date(dataInicio);
            dataOk = dataOk && dataTransacao >= dataInicioObj;
        }
        if (dataFim) {
            const dataFimObj = new Date(dataFim);
            dataOk = dataOk && dataTransacao <= dataFimObj;
        }

        const instituicaoOk = instituicaoSelecionada === "" || instituicao.includes(instituicaoSelecionada);

        linha.style.display = (dataOk && instituicaoOk) ? '' : 'none';
    });
}

document.getElementById('dataInicio').addEventListener('input', aplicarFiltros);
document.getElementById('dataFim').addEventListener('input', aplicarFiltros);
document.getElementById('filtroInstituicao').addEventListener('change', aplicarFiltros);