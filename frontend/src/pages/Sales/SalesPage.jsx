import styled from 'styled-components';
import { useState } from 'react';
import { useCart } from '../../context/CartContextDef';
import { useTable } from '../../context/TableContextDef';
import { useNavigate } from 'react-router-dom';
import { motion } from 'framer-motion';
import { FaSearch, FaTrash } from 'react-icons/fa';
import muamba from '../../assets/Muamba_de_galinha.jpg';
import mufete from '../../assets/Mufete.jpeg';
import placeholder from '../../assets/placeholder.jpg';

const Container = styled.div`
  display: flex;
  gap: 24px;
`;

const LeftPanel = styled(motion.div)`
  flex: 1; /* Changed from width: 60% to fill available space */
`;

const RightPanel = styled(motion.div)`
  width: 350px;
  background: #FFFFFF;
  padding: 16px; /* Reduced from 24px */
  border-radius: 12px;
  box-shadow: 0 4px 12px rgba(0, 0, 0, 0.05);
`;

const OrderList = styled.div`
  display: flex;
  flex-direction: column;
  gap: 12px;
`;

const OrderItem = styled.div`
  display: flex;
  justify-content: space-between;
  padding: 12px;
  background: #F8F9FA;
  border-radius: 8px;
`;

const TableInfo = styled.div`
  margin-bottom: 24px;
  font-size: 14px;
  color: #6C757D;
`;

const Catalog = styled.div`
  display: grid;
  grid-template-columns: repeat(auto-fill, minmax(200px, 1fr));
  gap: 24px;
`;

const SearchBar = styled.div`
  display: flex;
  margin-bottom: 24px;
`;

const Input = styled.input`
  flex: 1;
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
  margin-left: 12px;
  font-size: 14px;
  &:hover {
    background: linear-gradient(135deg, #218838, #1E7E34);
  }
`;

const Total = styled.div`
  margin: 24px 0;
  font-size: 18px;
  font-weight: 600;
`;

const menuItems = [
  { id: 1, name: 'Funge', price: 500, image: muamba, category: 'Principal' },
  { id: 2, name: 'Calulu', price: 1200, image: mufete, category: 'Principal' },
  { id: 3, name: 'Moamba de Galinha', price: 1500, image: muamba, category: 'Principal' },
  { id: 4, name: 'Mufete de Peixe', price: 1800, image: mufete, category: 'Principal' },
  { id: 5, name: 'Cabidela', price: 1300, image: muamba, category: 'Principal' },
  { id: 6, name: 'Feijão de óleo de palma', price: 800, image: mufete, category: 'Acompanhamento' },
  { id: 7, name: 'Kizaca', price: 1000, image: muamba, category: 'Acompanhamento' },
  { id: 8, name: 'Ginguba torrada', price: 300, image: mufete, category: 'Lanche' },
  { id: 9, name: 'Kifula', price: 1100, image: muamba, category: 'Principal' },
  { id: 10, name: 'Funbuá', price: 600, image: mufete, category: 'Acompanhamento' },
];

function SalesPage() {
  const { cart, addToCart, removeFromCart, getTotal, submitOrder } = useCart();
  const { selectedTable } = useTable();
  const navigate = useNavigate();
  const [search, setSearch] = useState('');
  const [category, setCategory] = useState('Todas');

  const tableId = selectedTable ? selectedTable.id : null;
  const tableCart = cart[tableId ?? 'none'] || [];
  const tableDisplay = selectedTable ? `Mesa ${selectedTable.id}` : 'Nenhuma';
  const tableStatus = selectedTable ? selectedTable.status : 'Nenhum';

  const filteredItems = menuItems.filter(
    (item) =>
      (category === 'Todas' || item.category === category) &&
      item.name.toLowerCase().includes(search.toLowerCase())
  );

  return (
    <Container>
      <LeftPanel
        initial={{ x: -100 }}
        animate={{ x: 0 }}
        transition={{ duration: 0.3 }}
      >
        <SearchBar>
          <Input
            type="text"
            placeholder="Pesquisar por nome ou código"
            value={search}
            onChange={(e) => setSearch(e.target.value)}
          />
          <Button><FaSearch /></Button>
        </SearchBar>
        <select onChange={(e) => setCategory(e.target.value)} style={{ marginBottom: '24px', padding: '12px', borderRadius: '8px', border: '1px solid #E9ECEF' }}>
          <option value="Todas">Todas as Categorias</option>
          <option value="Principal">Principal</option>
          <option value="Acompanhamento">Acompanhamento</option>
          <option value="Lanche">Lanche</option>
        </select>
        <Catalog>
          {filteredItems.map((item) => (
            <div
              key={item.id}
              onClick={() => addToCart(item, tableId)}
              className="card"
              style={{ textAlign: 'center', cursor: 'pointer' }}
            >
              <img
                src={item.image}
                alt={item.name}
                style={{ width: '100%', height: '120px', objectFit: 'cover', borderRadius: '8px', marginBottom: '12px' }}
                onError={(e) => (e.target.src = 'https://via.placeholder.com/200')}
              />
              <h3 style={{ fontSize: '16px', marginBottom: '8px' }}>{item.name}</h3>
              <p style={{ fontSize: '14px', color: '#6C757D' }}>{item.price.toFixed(2)} AOA</p>
            </div>
          ))}
        </Catalog>
      </LeftPanel>
      <RightPanel
        initial={{ x: 100 }}
        animate={{ x: 0 }}
        transition={{ duration: 0.3 }}
      >
        <h2>Venda - {tableDisplay}</h2>
        <TableInfo>
          <p><strong>ID da Mesa:</strong> {tableDisplay}</p>
          <p><strong>Estado:</strong> {tableStatus}</p>
        </TableInfo>
        <OrderList>
          {tableCart.map((item) => (
            <OrderItem key={item.id}>
              <span>{item.name} x {item.quantity}</span>
              <span>{(item.price * item.quantity).toFixed(2)} AOA</span>
              <FaTrash
                onClick={() => removeFromCart(item.id, tableId)}
                style={{ cursor: 'pointer', color: '#FF6B6B' }}
              />
            </OrderItem>
          ))}
        </OrderList>
        <Total>Total: {getTotal(tableId).toFixed(2)} AOA</Total>
        <Button onClick={() => submitOrder(tableId)}>Submeter Pedido</Button>
        <Button
          onClick={() =>
            navigate('/payment', { state: { tableId, tableCart, total: getTotal(tableId) } })
          }
        >
          Ir para Pagamento
        </Button>
      </RightPanel>
    </Container>
  );
}

export default SalesPage;