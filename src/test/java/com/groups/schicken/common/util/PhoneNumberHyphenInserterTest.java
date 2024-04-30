package com.groups.schicken.common.util;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest("spring.profiles.include=dev")
class PhoneNumberHyphenInserterTest {

    @Test
    public void insertHyphenTest(){
        String[] nums = "01012341234,010-0101111,010-1234-1234,0104455667799,101ff223344".split(",");

        for (int i = 0; i < nums.length; i++) {
            if(i == 0){
                assertNotEquals(nums[i], PhoneNumberHyphenInserter.hyphenInsert(nums[i]));
            } else {
                assertEquals(nums[i], PhoneNumberHyphenInserter.hyphenInsert(nums[i]));
            }
        }
    }
}
