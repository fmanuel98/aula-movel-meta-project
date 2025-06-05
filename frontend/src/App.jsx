import { Routes, Route } from 'react-router-dom';
import styled from 'styled-components';
import Sidebar from './components/Sidebar/Sidebar';
import LoginPage from './pages/Login/LoginPage';
import DashboardPage from './pages/Dashboard/DashboardPage';
import SalesPage from './pages/Sales/SalesPage';
import PaymentPage from './pages/Payment/PaymentPage';
import ProductsPage from './pages/Products/ProductsPage';
import ReportsPage from './pages/Reports/ReportsPage';
import CashClosurePage from './pages/CashClosure/CashClosurePage';
import SettingsPage from './pages/Settings/SettingsPage';
import SupportPage from './pages/Support/SupportPage';
import OrdersPage from './pages/Orders/OrdersPage';

const AppContainer = styled.div`
  display: flex;
`;

const Content = styled.div`
  margin-left: 160px; 
  padding: 24px;
  width: 100%;
`;

function App() {
  return (
    <Routes>
      <Route
        path="/"
        element={
          <AppContainer>
            <Sidebar />
            <Content><SalesPage /></Content>
          </AppContainer>
        }
      />
      <Route
        path="/login"
        element={
          <AppContainer>
            <Sidebar />
            <Content><LoginPage /></Content>
          </AppContainer>
        }
      />
      <Route
        path="/dashboard"
        element={
          <AppContainer>
            <Sidebar />
            <Content><DashboardPage /></Content>
          </AppContainer>
        }
      />
      <Route
        path="/sales"
        element={
          <AppContainer>
            <Sidebar />
            <Content><SalesPage /></Content>
          </AppContainer>
        }
      />
      <Route
        path="/payment"
        element={
          <AppContainer>
            <Sidebar />
            <Content><PaymentPage /></Content>
          </AppContainer>
        }
      />
      <Route
        path="/products"
        element={
          <AppContainer>
            <Sidebar />
            <Content><ProductsPage /></Content>
          </AppContainer>
        }
      />
      <Route
        path="/reports"
        element={
          <AppContainer>
            <Sidebar />
            <Content><ReportsPage /></Content>
          </AppContainer>
        }
      />
      <Route
        path="/cash-closure"
        element={
          <AppContainer>
            <Sidebar />
            <Content><CashClosurePage /></Content>
          </AppContainer>
        }
      />
      <Route
        path="/settings"
        element={
          <AppContainer>
            <Sidebar />
            <Content><SettingsPage /></Content>
          </AppContainer>
        }
      />
      <Route
        path="/support"
        element={
          <AppContainer>
            <Sidebar />
            <Content><SupportPage /></Content>
          </AppContainer>
        }
      />
      <Route
        path="/orders"
        element={
          <AppContainer>
            <Sidebar />
            <Content><OrdersPage /></Content>
          </AppContainer>
        }
      />
    </Routes>
  );
}

export default App;