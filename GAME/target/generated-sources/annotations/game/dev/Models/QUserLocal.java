package game.dev.Models;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QUserLocal is a Querydsl query type for UserLocal
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QUserLocal extends EntityPathBase<UserLocal> {

    private static final long serialVersionUID = 124770577L;

    public static final QUserLocal userLocal = new QUserLocal("userLocal");

    public final ArrayPath<byte[], Byte> avatar = createArray("avatar", byte[].class);

    public final StringPath description = createString("description");

    public final StringPath login = createString("login");

    public final StringPath password = createString("password");

    public final DatePath<java.sql.Date> updateDate = createDate("updateDate", java.sql.Date.class);

    public final ComparablePath<java.util.UUID> userId = createComparable("userId", java.util.UUID.class);

    public QUserLocal(String variable) {
        super(UserLocal.class, forVariable(variable));
    }

    public QUserLocal(Path<? extends UserLocal> path) {
        super(path.getType(), path.getMetadata());
    }

    public QUserLocal(PathMetadata metadata) {
        super(UserLocal.class, metadata);
    }

}

