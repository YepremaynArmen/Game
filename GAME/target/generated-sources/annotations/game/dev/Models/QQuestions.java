package game.dev.Models;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QQuestions is a Querydsl query type for Questions
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QQuestions extends EntityPathBase<Questions> {

    private static final long serialVersionUID = -1979289602L;

    public static final QQuestions questions = new QQuestions("questions");

    public final DatePath<java.sql.Date> aprovedAt = createDate("aprovedAt", java.sql.Date.class);

    public final ComparablePath<java.util.UUID> aprovedBy = createComparable("aprovedBy", java.util.UUID.class);

    public final DatePath<java.sql.Date> changeAt = createDate("changeAt", java.sql.Date.class);

    public final ComparablePath<java.util.UUID> changeBy = createComparable("changeBy", java.util.UUID.class);

    public final NumberPath<Long> complexity = createNumber("complexity", Long.class);

    public final DatePath<java.sql.Date> createdAt = createDate("createdAt", java.sql.Date.class);

    public final ComparablePath<java.util.UUID> createdBy = createComparable("createdBy", java.util.UUID.class);

    public final StringPath description = createString("description");

    public final StringPath groups = createString("groups");

    public final NumberPath<Long> questionId = createNumber("questionId", Long.class);

    public final StringPath status = createString("status");

    public final StringPath task = createString("task");

    public QQuestions(String variable) {
        super(Questions.class, forVariable(variable));
    }

    public QQuestions(Path<? extends Questions> path) {
        super(path.getType(), path.getMetadata());
    }

    public QQuestions(PathMetadata metadata) {
        super(Questions.class, metadata);
    }

}

