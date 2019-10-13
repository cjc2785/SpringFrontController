package com.ss.lms.dao;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.ss.lms.db.*;
import com.ss.lms.model.*;

@Component
public class LibraryBranchDao {

	@Autowired
	private Db db;



	public List<LibraryBranch> getAll() throws SQLException {
		String query = "SELECT * FROM library.tbl_library_branch";
		return db.withQuery(query, row -> {
			int branchId = row.getInt("branchId");
			String branchName = row.getString("branchName");
			String branchAddress = row.getString("branchAddress");
			return new LibraryBranch(branchId, branchName, branchAddress);
		});
	}

	//Get all branches where the borrower has at least 1 book checked out
	public List<LibraryBranch> getAll(Borrower borrower) throws SQLException {

		String query = "SELECT DISTINCT lb.* FROM library.tbl_library_branch lb "
				+ "JOIN library.tbl_book_loans bl ON bl.branchId=lb.branchId " 
				+ "JOIN library.tbl_borrower bor ON bor.cardNo=bl.cardNo " 
				+ "WHERE bor.cardNo=?";

		return db.withQuery(
				query, 
				row -> {
					int branchId = row.getInt("branchId");
					String branchName = row.getString("branchName");
					String branchAddress = row.getString("branchAddress");
					return new LibraryBranch(branchId, branchName, branchAddress);
				},
				parameterList -> {
					parameterList.setInt(1, borrower.getBorrowerCardNumber());
				});
	}

	public Optional<LibraryBranch> get(int branchId) throws SQLException {

		String query = "SELECT * FROM library.tbl_library_branch " +
				"WHERE branchId=?";


		return db.withQueryOne(
				query, 
				row -> {
					String branchName = row.getString("branchName");
					String branchAddress = row.getString("branchAddress");
					return new LibraryBranch(branchId, branchName, branchAddress);
				},
				parameterList -> {
					parameterList.setInt(1, branchId);
				});
	}


	public void delete(LibraryBranch branch) throws SQLException {

		String query = "DELETE FROM library.tbl_library_branch " + 
				"WHERE branchId=?";

		db.withUpdate(query, parameterList -> {
			parameterList.setInt(1, branch.getBranchId());
		});
	}


	public void insert(LibraryBranch branch) throws SQLException {

		String query = "INSERT INTO library.tbl_library_branch VALUES " + 
				"(?,?,?) ";

		db.withUpdate(query, parameterList -> {
			parameterList.setInt(1, branch.getBranchId());
			parameterList.setString(2, branch.getBranchName());
			parameterList.setString(3, branch.getBranchAddress());
		});
	}

	public void update(LibraryBranch branch) throws SQLException {

		String query = "UPDATE library.tbl_library_branch SET " + 
				"branchName=?, " +
				"branchAddress=? " +
				"WHERE branchId=?";

		db.withUpdate(query, parameterList -> {
			parameterList.setString(1, branch.getBranchName());
			parameterList.setString(2, branch.getBranchAddress());
			parameterList.setInt(3, branch.getBranchId());
		});
	}
}
