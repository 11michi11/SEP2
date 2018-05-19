package test;

import model.Family;
import org.junit.jupiter.api.Test;

import java.util.UUID;

import static junit.framework.Assert.fail;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class FamilyTest {

    @Test
    void idConstructorTest(){
        Family f = new Family("id");

        assertEquals("id", f.getId());
    }

    @Test
    void UUIDConstructorTest(){
        Family f = new Family();

        try{
            UUID uuid = UUID.fromString(f.getId());
        } catch (IllegalArgumentException exception){
            fail();
        }
    }



}
