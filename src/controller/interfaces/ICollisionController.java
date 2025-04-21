package controller.interfaces;

import model.interfaces.IEntity;

public interface ICollisionController {
    void checkCollisions();

    boolean checkCollision(IEntity entity1, IEntity entity2);

    <T extends IEntity> boolean checkCollisionWithType(IEntity entity, Class<T> entityClass);
}
