import React, { useState, useEffect } from 'react';
import styled from 'styled-components';
import { motion } from 'framer-motion';
import { FaPlus, FaEdit, FaTimes, FaTrash } from 'react-icons/fa'; // Added FaTrash
import api from '../api';

const Container = styled(motion.div)`
  padding: 24px;
  max-width: 1200px;
  margin: 0 auto;
`;

const Title = styled.h2`
  font-size: 28px;
  margin-bottom: 24px;
  color: #343A40;
`;

const FormSection = styled(motion.div)`
  background: #FFFFFF;
  border-radius: 12px;
  padding: 16px;
  box-shadow: 0 4px 12px rgba(0, 0, 0, 0.05);
  margin-bottom: 24px;
`;

const FormTitle = styled.h3`
  font-size: 20px;
  margin-bottom: 16px;
  color: #343A40;
`;

const Form = styled.form`
  display: grid;
  gap: 12px;
  grid-template-columns: 1fr 1fr;
  @media (max-width: 768px) {
    grid-template-columns: 1fr;
  }
`;

const Input = styled.input`
  padding: 12px;
  border: 1px solid #E9ECEF;
  border-radius: 8px;
  background: #F8F9FA;
  font-size: 14px;
  width: 100%;
  &:disabled {
    background: #E9ECEF;
    cursor: not-allowed;
  }
`;

const FileInput = styled.input`
  padding: 12px;
  border: 1px solid #E9ECEF;
  border-radius: 8px;
  background: #F8F9FA;
  font-size: 14px;
`;

const Button = styled.button`
  background: linear-gradient(135deg, #28A745, #218838);
  color: #FFFFFF;
  padding: 12px;
  border: none;
  border-radius: 8px;
  font-size: 14px;
  display: flex;
  align-items: center;
  justify-content: center;
  gap: 8px;
  cursor: pointer;
  &:hover {
    background: linear-gradient(135deg, #218838, #1E7E34);
  }
  &:disabled {
    background: #6C757D;
    cursor: not-allowed;
  }
`;

const CancelButton = styled(Button)`
  background: linear-gradient(135deg, #DC3545, #C82333);
  &:hover {
    background: linear-gradient(135deg, #C82333, #B02A37);
  }
`;

const DeleteButton = styled.button` // New DeleteButton styled component
  background: linear-gradient(135deg, #DC3545, #C82333);
  color: #FFFFFF;
  padding: 8px;
  border: none;
  border-radius: 8px;
  font-size: 14px;
  cursor: pointer;
  display: flex;
  align-items: center;
  gap: 4px;
  margin: 8px auto 0;
  &:hover {
    background: linear-gradient(135deg, #C82333, #B02A37);
  }
`;

const ProductGrid = styled.div`
  display: grid;
  grid-template-columns: repeat(auto-fill, minmax(200px, 1fr));
  gap: 24px;
`;

const ProductCard = styled(motion.div)`
  background: #FFFFFF;
  border-radius: 12px;
  padding: 16px;
  box-shadow: 0 4px 12px rgba(0, 0, 0, 0.05);
  text-align: center;
  position: relative;
`;

const ProductImage = styled.img`
  width: 100%;
  height: 120px;
  object-fit: cover;
  border-radius: 8px;
  margin-bottom: 12px;
`;

const ProductName = styled.h3`
  font-size: 16px;
  margin-bottom: 8px;
  color: #343A40;
`;

const ProductPrice = styled.p`
  font-size: 14px;
  color: #6C757D;
  margin-bottom: 8px;
`;

const ProductQuantity = styled.p`
  font-size: 14px;
  color: #6C757D;
  margin-bottom: 8px;
`;

const EditButton = styled.button`
  background: #FFC107;
  color: #FFFFFF;
  padding: 8px;
  border: none;
  border-radius: 8px;
  font-size:zilla: 14px;
  cursor: pointer;
  display: flex;
  align-items: center;
  gap: 4px;
  margin: 0 auto;
  &:hover {
    background: #E0A800;
  }
`;

const ErrorMessage = styled.p`
  color: #DC3545;
  font-size: 14px;
  margin-top: 8px;
`;

const LoadingMessage = styled.p`
  font-size: 16px;
  color: #343A40;
  text-align: center;
`;

const ProdutoForm = () => {
  const [produtoId, setProdutoId] = useState('');
  const [nome, setNome] = useState('');
  const [preco, setPreco] = useState('');
  const [quantidade, setQuantidade] = useState('');
  const [image, setImage] = useState(null);
  const [produtos, setProdutos] = useState([]);
  const [isEditing, setIsEditing] = useState(false);
  const [loading, setLoading] = useState(true);
  const [error, setError] = useState(null);

  useEffect(() => {
    const fetchProdutos = async () => {
      try {
        const response = await api.get('/produtos');
        setProdutos(response.data);
        setLoading(false);
      } catch (error) {
        console.error('Erro ao buscar produtos:', error);
        setError('Falha ao carregar produtos.');
        setLoading(false);
      }
    };
    fetchProdutos();
  }, []);

  const clearForm = () => {
    setProdutoId('');
    setNome('');
    setPreco('');
    setQuantidade('');
    setImage(null);
    setIsEditing(false);
    setError(null);
  };

  const handleSubmit = async (e) => {
    e.preventDefault();
    setError(null);
    try {
      if (isEditing) {
        const id = Number(produtoId);
        console.log('Enviando PUT para:', `/produtos/${id}`, {
          nome,
          preco: parseFloat(preco),
          quantidade: parseInt(quantidade),
        });
        const response = await api.put(`/produtos/${id}`, {
          nome,
          preco: parseFloat(preco),
          quantidade: parseInt(quantidade),
        });
        console.log('Resposta do PUT:', response.data);
        setProdutos(
          produtos.map((p) => (p.id === id ? response.data : p))
        );
        alert('Produto atualizado com sucesso!');
      } else {
        const formData = new FormData();
        formData.append('image', image);
        formData.append('nome', nome);
        formData.append('preco', parseFloat(preco));
        formData.append('quantidade', parseInt(quantidade));
        console.log('Enviando POST com FormData:', [...formData.entries()]);
        const response = await api.post('/produtos', formData, {
          headers: {
            'Content-Type': 'multipart/form-data',
          },
        });
        console.log('Resposta do POST:', response.data);
        setProdutos([...produtos, response.data]);
        alert('Produto adicionado com sucesso!');
      }
      clearForm();
    } catch (error) {
      console.error('Erro ao processar produto:', {
        message: error.message,
        response: error.response?.data,
        status: error.response?.status,
      });
      setError(
        error.response?.status === 404
          ? 'Produto não encontrado.'
          : error.response?.status === 400
          ? 'Dados inválidos. Verifique os campos.'
          : 'Erro ao processar produto. Veja o console para detalhes.'
      );
    }
  };

  const handleEdit = (produto) => {
    console.log('Editando produto:', produto);
    setProdutoId(produto.id);
    setNome(produto.nome);
    setPreco(produto.preco.toString());
    setQuantidade(produto.quantidade.toString());
    setImage(null);
    setIsEditing(true);
    window.scrollTo({ top: 0, behavior: 'smooth' });
  };

  const handleDelete = async (id) => {
    if (!window.confirm('Tem certeza que deseja apagar este produto?')) {
      return;
    }

    setError(null);
    try {
      console.log('Enviando DELETE para:', `/produtos/${id}`);
      await api.delete(`/produtos/${id}`);
      setProdutos(produtos.filter((p) => p.id !== id));
      alert('Produto apagado com sucesso!');
    } catch (error) {
      console.error('Erro ao apagar produto:', {
        message: error.message,
        response: error.response?.data,
        status: error.response?.status,
      });
      setError(
        error.response?.status === 404
          ? 'Produto não encontrado.'
          : error.response?.status === 400
          ? 'Erro nos dados fornecidos.'
          : 'Erro ao apagar produto. Veja o console para detalhes.'
      );
    }
  };

  if (loading) {
    return <LoadingMessage>Carregando produtos...</LoadingMessage>;
  }

  return (
    <Container
      initial={{ opacity: 0 }}
      animate={{ opacity: 1 }}
      transition={{ duration: 0.5 }}
    >
      <Title>{isEditing ? 'Atualizar Produto' : 'Adicionar Produto'}</Title>
      <FormSection
        initial={{ y: 20 }}
        animate={{ y: 0 }}
        transition={{ duration: 0.3 }}
      >
        <FormTitle>{isEditing ? 'Editar Produto' : 'Novo Produto'}</FormTitle>
        <Form onSubmit={handleSubmit}>
          {isEditing && (
            <Input
              type="number"
              placeholder="ID do Produto"
              value={produtoId}
              readOnly
              disabled
            />
          )}
          <Input
            type="text"
            placeholder="Nome"
            value={nome}
            onChange={(e) => setNome(e.target.value)}
            required
          />
          <Input
            type="number"
            placeholder="Preço"
            value={preco}
            onChange={(e) => setPreco(e.target.value)}
            step="0.01"
            required
          />
          <Input
            type="number"
            placeholder="Quantidade"
            value={quantidade}
            onChange={(e) => setQuantidade(e.target.value)}
            required
          />
          {!isEditing && (
            <FileInput
              type="file"
              onChange={(e) => setImage(e.target.files[0])}
              accept="image/*"
            />
          )}
          <Button type="submit" disabled={loading}>
            {isEditing ? (
              <>
                <FaEdit /> Atualizar
              </>
            ) : (
              <>
                <FaPlus /> Adicionar
              </>
            )}
          </Button>
          {isEditing && (
            <CancelButton type="button" onClick={clearForm}>
              <FaTimes /> Cancelar
            </CancelButton>
          )}
        </Form>
        {error && <ErrorMessage>{error}</ErrorMessage>}
      </FormSection>

      <Title>Lista de Produtos</Title>
      <ProductGrid>
        {produtos.map((produto) => (
          <ProductCard
            key={produto.id}
            initial={{ y: 20 }}
            animate={{ y: 0 }}
            transition={{ duration: 0.3 }}
          >
            <ProductImage
              src={produto.imageUrl || 'https://via.placeholder.com/200'}
              alt={produto.nome}
              onError={(e) => (e.target.src = 'https://via.placeholder.com/200')}
            />
            <ProductName>{produto.nome}</ProductName>
            <ProductPrice>{produto.preco.toFixed(2)} AOA</ProductPrice>
            <ProductQuantity>Quantidade: {produto.quantidade}</ProductQuantity>
            <EditButton onClick={() => handleEdit(produto)}>
              <FaEdit /> Editar
            </EditButton>
            <DeleteButton onClick={() => handleDelete(produto.id)}>
              <FaTrash /> Apagar
            </DeleteButton>
          </ProductCard>
        ))}
      </ProductGrid>
    </Container>
  );
};

export default ProdutoForm;