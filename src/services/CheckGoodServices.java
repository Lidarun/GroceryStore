package services;

import java.util.List;
import models.CheckGood;

public interface CheckGoodServices {
    public void save(long idTbChecks, long idTbGoods, int count);
    public void update(long id, long idTbChecks, long idTbGoods, int count);
    public List<CheckGood> findAll();
    public CheckGood findById(long id);
    public void delete(long id);
}
