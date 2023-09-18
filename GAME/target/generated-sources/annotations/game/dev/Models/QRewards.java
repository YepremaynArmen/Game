package game.dev.Models;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QRewards is a Querydsl query type for Rewards
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QRewards extends EntityPathBase<Rewards> {

    private static final long serialVersionUID = 1426701845L;

    public static final QRewards rewards = new QRewards("rewards");

    public final DatePath<java.sql.Date> changeAt = createDate("changeAt", java.sql.Date.class);

    public final ComparablePath<java.util.UUID> changeBy = createComparable("changeBy", java.util.UUID.class);

    public final DatePath<java.sql.Date> createdAt = createDate("createdAt", java.sql.Date.class);

    public final ComparablePath<java.util.UUID> createdBy = createComparable("createdBy", java.util.UUID.class);

    public final NumberPath<Long> currencyId = createNumber("currencyId", Long.class);

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final NumberPath<Long> questionId = createNumber("questionId", Long.class);

    public final StringPath status = createString("status");

    public final NumberPath<Double> value = createNumber("value", Double.class);

    public QRewards(String variable) {
        super(Rewards.class, forVariable(variable));
    }

    public QRewards(Path<? extends Rewards> path) {
        super(path.getType(), path.getMetadata());
    }

    public QRewards(PathMetadata metadata) {
        super(Rewards.class, metadata);
    }

}

