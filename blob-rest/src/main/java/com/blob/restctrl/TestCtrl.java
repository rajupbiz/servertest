package com.blob.restctrl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.blob.dao.ContactDao;
import com.blob.model.Contact;

@RestController
@RequestMapping(value="/contact")
public class TestCtrl {
	
	@Resource
	private ContactDao contactDao;

	@RequestMapping(value="/{id}", method=RequestMethod.GET, produces=MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody Contact getContact(@PathVariable Long id){
		System.out.println("  id ===>   "+id);
		Contact contact = contactDao.findOne(id);
		return contact;
	}
	
	@RequestMapping(value="", method=RequestMethod.GET, produces=MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody List<Contact> getAllContacts(){
		System.out.println("  getAllContacts ===>   ");
		List<Contact> contacts = contactDao.findAll();
		return contacts;
	}
	
	@RequestMapping(value="", method=RequestMethod.POST, consumes=MediaType.APPLICATION_JSON_VALUE, produces=MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody Long createContact(Contact c){
		System.out.println("  create contact "+c.getFirstName());
		
		if(c != null && c.getFirstName() != null){
			c = contactDao.save(c);
		}
		return c.getId();
	}
	
	/*@RequestMapping(value="/", method=RequestMethod.GET, produces=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Contact> hello(){
		
		System.out.println("  hello ===>   ");
		
		Contact c = new Contact();
		c.setId(1L);
		c.setFirstName("Max");
		
		c = new Contact();
		c.setId(2L);
		c.setFirstName("Mark");
		
		return new ResponseEntity<Contact>(c, HttpStatus.OK);
	}*/
}
