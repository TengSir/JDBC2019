package dao;

public class OrderDAOImp extends BaseDAOImp implements  OrderDAO {
    @Override
    public boolean addOrder(Order order) {
        return false;
    }

    @Override
    public boolean deleteOrder(int oderid) {
        return false;
    }

    @Override
    public boolean updateOrder(Order o) {
        return false;
    }

    @Override
    public List<Order> listALlOrders() {
        return null;
    }
}
