package controller;

import controller.interfaces.ICollisionController;
import model.interfaces.IEntity;

public class CollisionController implements ICollisionController {

    @Override
    public void checkCollisions() {

    }

    @Override
    public boolean checkCollision(IEntity entity1, IEntity entity2) {
        return false;
    }

    @Override
    public <T extends IEntity> boolean checkCollisionWithType(IEntity entity, Class<T> entityClass) {
        return false;
    }
}
