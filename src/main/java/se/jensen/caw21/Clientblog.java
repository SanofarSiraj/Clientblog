package se.jensen.caw21;

import java.io.StringWriter;
import java.io.Writer;

import com.github.cliftonlabs.json_simple.JsonObject;
import com.github.cliftonlabs.json_simple.JsonKey;
import com.github.cliftonlabs.json_simple.Jsonable;

    public class Clientblog implements Jsonable {
        public int id;
        public String title;
        public String content;

        enum keys implements JsonKey {
            ID("id"),
            TITLE("title"),
            CONTENT("content");

            private final Object value;

            /**
             * Instantiates a JsonKey with the provided value.
             *
             * @param value represents a valid default for the key.
             */
            keys(final Object value) {
                this.value = value;
            }

            @Override
            public String getKey() {
                return this.name().toLowerCase();
            }

            @Override
            public Object getValue() {
                return this.value;
            }
        }

        public Clientblog() {
        }

        public Clientblog(String title,String content) {
            this.title = title;
            this.content=content;
        }

        public Clientblog(int id, String title, String content) {
            this.id = id;
            this.title = title;
            this.content = content;
        }

        public String getTitle() {

            return this.title;
        }

        public int getId() {
            return this.id;
        }

        public String getContent() {

            return this.content;
        }

        public void setId(int id) {
            this.id = id;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public void setContent(String content) {
            this.content = content;
        }

        @Override
        public String toJson() {
            final StringWriter writable = new StringWriter();
            try {
                this.toJson(writable);
            } catch (final Exception e) {
                /* See java.io.StringWriter. */
            }
            return writable.toString();
        }

        @Override
        public void toJson(final Writer writable) {
            try {
                final JsonObject json = new JsonObject();
                json.put(keys.TITLE.getKey(), this.getTitle());
                json.put(keys.CONTENT.getKey(), this.getContent());
                json.put(keys.ID.getKey(), this.getId());
                json.toJson(writable);
            } catch (Exception e) {
                System.out.println("Exception: " + e);
            }
        }

        @Override
        public String toString() {
            return "JsonSimpleExample [id=" + this.id + ", title=" + this.title + ", rating=" + this.content + "]";
        }
    }

