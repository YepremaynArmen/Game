package game.dev.Models;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QUserRoles is a Querydsl query type for UserRoles
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QUserRoles extends EntityPathBase<UserRoles> {

    private static final long serialVersionUID = 130320483L;

    public static final QUserRoles userRoles = new QUserRoles("userRoles");

    public final NumberPath<Long> roleId = createNumber("roleId", Long.class);

    public final StringPath roleName = createString("roleName");

    public final ComparablePath<java.util.UUID> userId = createComparable("userId", java.util.UUID.class);

    public QUserRoles(String variable) {
        super(UserRoles.class, forVariable(variable));
    }

    public QUserRoles(Path<? extends UserRoles> path) {
        super(path.getType(), path.getMetadata());
    }

    public QUserRoles(PathMetadata metadata) {
        super(UserRoles.class, metadata);
    }

}

