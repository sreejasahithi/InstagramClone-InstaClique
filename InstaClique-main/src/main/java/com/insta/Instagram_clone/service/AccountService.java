package com.insta.Instagram_clone.service;

import com.insta.Instagram_clone.model.*;
import com.insta.Instagram_clone.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.stream.Collectors;
import java.util.ArrayList;
import java.util.List;

@Service
public class AccountService implements search_interface {
	@Autowired
	private AccountRepository accountRepository;

	public List<AccountDto> searchByUsername(String username) {
		List<Account> accounts = accountRepository.findByUsernameContaining(username);

		return accounts.stream()
				.map(account -> new AccountDto(account.getId(), account.getUsername(), account.getEmail(),
						account.getPassword()))
				.collect(Collectors.toList());
	}

	public AccountDto searchByUserId(String userId) {
		Account account = accountRepository.findById(userId).orElse(null);
		if (account != null) {

			return new AccountDto(account.getId(), account.getUsername(),
					account.getEmail(), account.getPassword());
		}
		return null;
	}

	public List<String> getAllUserIds(String id) {
		List<Account> accounts = accountRepository.findAll();
		List<String> userIds = new ArrayList<>();
		for (Account account : accounts) {
			userIds.add(account.getId());
		}
		return userIds;
	}

}
