import React, { useState } from "react";
import api from "../api";

const ProdutoForm = () => {
  const [nome, setNome] = useState("");
  const [preco, setPreco] = useState("");
  const [quantidade, setQuantidade] = useState("");
  const [image, setimage] = useState(null);

  function clenaForm() {
    setNome("");
    setPreco("");
    setQuantidade("");
    setimage(null);
  }

  const handleSubmit = async (e) => {
    e.preventDefault();
    try {
      const formData = new FormData();
      formData.append("image", image);
      formData.append("nome", nome);
      formData.append("preco", preco);
      formData.append("quantidade", quantidade);

      await api.post("/produtos", formData, {
      headers: {
        'Content-Type': 'multipart/form-data',
      }});
      alert("Produto adicionado com sucesso!");
      clenaForm();
    } catch (error) {
      console.error("Erro ao adicionar produto:", error);
    }
  };

  return (
    <form onSubmit={handleSubmit}>
      <h2>Adicionar Produto</h2>
      <input
        type="text"
        placeholder="Nome"
        value={nome}
        onChange={(e) => setNome(e.target.value)}
      />
      <input
        type="number"
        placeholder="Preço"
        value={preco}
        onChange={(e) => setPreco(e.target.value)}
      />
      <input
        type="number"
        placeholder="Quantidade"
        value={quantidade}
        onChange={(e) => setQuantidade(e.target.value)}
      />
      <input
        type="file"
        onChange={(e) => setimage(e.target.files[0])}
      />
      <button type="submit">Adicionar</button>
    </form>
  );
};

export default ProdutoForm;
