import styled from 'styled-components';
import { useState } from 'react';
import { useNavigate } from 'react-router-dom';
import { useTable } from '../../context/TableContextDef';
import { motion } from 'framer-motion';
import api from '../../api';

const Container = styled(motion.div)`
  padding: 24px;
`;

const Title = styled.h2`
  font-size: 28px;
  margin-bottom: 24px;
  color: #343A40;
`;

const TableGrid = styled.div`
  display: grid;
  grid-template-columns: repeat(auto-fill, minmax(200px, 1fr));
  gap: 24px;
`;

const TableCard = styled(motion.div)`
  background: #FFFFFF;
  border-radius: 12px;
  padding: 16px;
  text-align: center;
  box-shadow: 0 4px 12px rgba(0, 0, 0, 0.05);
  transition: transform 0.2s;
  &:hover {
    transform: translateY(-4px);
  }
`;

const TableId = styled.h3`
  font-size: 20px;
  margin-bottom: 12px;
  color: #343A40;
`;

const ButtonContainer = styled.div`
  display: flex;
  flex-direction: column;
  gap: 8px;
`;

const Button = styled.button`
  background: linear-gradient(135deg, #28A745, #218838);
  color: #FFFFFF;
  padding: 12px;
  border: none;
  border-radius: 8px;
  font-size: 14px;
  cursor: pointer;
  &:hover {
    background: linear-gradient(135deg, #218838, #1E7E34);
  }
`;

const OrderList = styled(motion.div)`
  background: #FFFFFF;
  border-radius: 12px;
  padding: 16px;
  margin-top: 12px;
  box-shadow: 0 4px 12px rgba(0, 0, 0, 0.05);
`;

const OrderItem = styled.div`
  padding: 8px 0;
  border-bottom: 1px solid #E9ECEF;
  &:last-child {
    border-bottom: none;
  }
`;

const OrderTitle = styled.h4`
  font-size: 16px;
  color: #343A40;
  margin-bottom: 8px;
`;

const OrderDetail = styled.p`
  font-size: 14px;
  color: #6C757D;
`;

const ErrorMessage = styled.p`
  color: #DC3545;
  font-size: 14px;
  text-align: center;
  margin-top: 12px;
`;

const tables = [1, 2, 3, 4, 5, 6, 7, 8, 9, 10];

function OrdersPage() {
  const { setSelectedTable } = useTable();
  const navigate = useNavigate();
  const [selectedTableId, setSelectedTableId] = useState(null);
  const [orders, setOrders] = useState([]);
  const [loading, setLoading] = useState(false);
  const [error, setError] = useState(null);

  const handleNewOrder = (tableId) => {
    setSelectedTable({ id: tableId });
    navigate('/sales');
  };

  const handleViewOrders = async (tableId) => {
    if (selectedTableId === tableId) {
      setSelectedTableId(null); // Fecha a lista se clicar novamente
      setOrders([]);
      return;
    }

    setSelectedTableId(tableId);
    setLoading(true);
    setError(null);

    try {
      const response = await api.get(`/compras?mesaId=${tableId}`);
      setOrders(response.data);
    } catch (err) {
      console.error('Erro ao buscar pedidos:', err);
      setError('Falha ao carregar pedidos. Verifique o console.');
      setOrders([]);
    } finally {
      setLoading(false);
    }
  };

  return (
    <Container
      initial={{ opacity: 0 }}
      animate={{ opacity: 1 }}
      transition={{ duration: 0.5 }}
    >
      <Title>Pedidos - Eva e Filhos</Title>
      <TableGrid>
        {tables.map((tableId) => (
          <TableCard
            key={tableId}
            initial={{ y: 20 }}
            animate={{ y: 0 }}
            transition={{ duration: 0.3 }}
          >
            <TableId>Mesa {tableId}</TableId>
            <ButtonContainer>
              <Button onClick={() => handleNewOrder(tableId)}>
                Novo Pedido
              </Button>
              <Button onClick={() => handleViewOrders(tableId)}>
                Ver Pedido
              </Button>
            </ButtonContainer>
            {selectedTableId === tableId && (
              <OrderList
                initial={{ opacity: 0, height: 0 }}
                animate={{ opacity: 1, height: 'auto' }}
                transition={{ duration: 0.3 }}
              >
                {loading ? (
                  <OrderDetail>Carregando...</OrderDetail>
                ) : error ? (
                  <ErrorMessage>{error}</ErrorMessage>
                ) : orders.length === 0 ? (
                  <OrderDetail>Sem pedidos registrados.</OrderDetail>
                ) : (
                  <>
                    <OrderTitle>Pedidos da Mesa {tableId}</OrderTitle>
                    {orders.map((order) => (
                      <OrderItem key={order.id}>
                        <OrderDetail>
                          Pedido #{order.id} - Total: {order.total?.toFixed(2) || '0.00'} AOA
                        </OrderDetail>
                        <OrderDetail>
                          Itens: {order.itens?.length || 0}
                        </OrderDetail>
                      </OrderItem>
                    ))}
                  </>
                )}
              </OrderList>
            )}
          </TableCard>
        ))}
      </TableGrid>
    </Container>
  );
}

export default OrdersPage;