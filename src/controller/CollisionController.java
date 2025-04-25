package controller;

import controller.interfaces.ICollisionController;
import model.interfaces.IEntity;
import model.interfaces.IMovable;

import java.awt.*;
import java.util.List;

public class CollisionController implements ICollisionController {

    @Override
    public void checkCollisions() {

    }

    @Override
    public boolean checkCollision(IEntity entity1, IEntity entity2) {
        if (entity1.isSolid() && entity2.isSolid()) {
            if (entity1.getHitBox().intersects(entity2.getHitBox())) {
                //System.out.println("Va Cham thang lon");
                return false;
            }
        } else if (entity1.isSolid() && !entity2.isSolid()) {
            return !entity1.getHitBox().intersects(entity2.getHitBox());
        }
        return true;
    }
    public boolean checkCollisionWithStatic(IMovable entity, List<? extends IEntity> staticEntities) {
        Rectangle nextHitBox = entity.getHitBox();
        for (IEntity staticEntity : staticEntities) {
            if (staticEntity.isSolid() && nextHitBox.intersects(staticEntity.getHitBox())) {
                //System.out.println("Va voi tuong roi");
                return true;
            } else if (!staticEntity.isSolid() && nextHitBox.intersects(staticEntity.getHitBox())) {
                return true;
            }
        }
        return false;
    }
    @Override
    public <T extends IEntity> boolean checkCollisionWithType(IEntity entity, Class<T> entityClass) {

        return false;
    }
}
