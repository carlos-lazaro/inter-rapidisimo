package com.example.presentation.util

import com.example.core.util.DataError
import com.example.domain.error.DomainError
import com.example.presentation.R
import com.example.presentation.util.UiText.StringResource

fun DomainError.asUiText(): UiText =
	when (this) {
		DomainError.UsernameEmpty -> StringResource(R.string.error_username_empty)
		DomainError.PasswordEmpty -> StringResource(R.string.error_password_empty)
		DomainError.InvalidAppVersion -> StringResource(R.string.error_invalid_app_version)
		DomainError.AppVersionOutdated -> StringResource(R.string.error_app_version_outdated)
		DomainError.Unknown -> StringResource(R.string.error_unknown)
		is DomainError.LocalError -> error.asUiText()
		is DomainError.RemoteError -> error.asUiText()
	}

fun DataError.asUiText(): UiText =
	when (this) {
		DataError.Local.DiskFull -> {
			StringResource(
				R.string.error_disk_full,
			)
		}

		DataError.Local.DataCorruption -> {
			StringResource(
				R.string.error_data_corrupted,
			)
		}

		DataError.Remote.BadRequest -> {
			StringResource(
				R.string.error_bad_request,
			)
		}

		DataError.Remote.RequestTimeout -> {
			StringResource(
				R.string.error_request_timeout,
			)
		}

		DataError.Remote.Unauthorized -> {
			StringResource(
				R.string.error_unauthorized,
			)
		}

		DataError.Remote.Forbidden -> {
			StringResource(
				R.string.error_forbidden,
			)
		}

		DataError.Remote.NotFound -> {
			StringResource(
				R.string.error_not_found,
			)
		}

		DataError.Remote.TooManyRequests -> {
			StringResource(
				R.string.error_too_many_requests,
			)
		}

		DataError.Remote.ClientError -> {
			StringResource(
				R.string.error_bad_request,
			)
		}

		DataError.Remote.NoInternet -> {
			StringResource(
				R.string.error_no_internet,
			)
		}

		DataError.Remote.Serialization -> {
			StringResource(
				R.string.error_serialization,
			)
		}

		DataError.Remote.ServerError -> {
			StringResource(
				R.string.error_server_error,
			)
		}

		DataError.Remote.ServiceUnavailable -> {
			StringResource(
				R.string.error_server_error,
			)
		}

		DataError.Remote.Conflict -> {
			StringResource(
				R.string.error_unknown,
			)
		}

		DataError.Remote.PayloadTooLarge -> {
			StringResource(
				R.string.error_unknown,
			)
		}

		DataError.Remote.Unknown -> {
			StringResource(
				R.string.error_unknown,
			)
		}

		DataError.Local.Unknown -> {
			StringResource(
				R.string.error_unknown,
			)
		}
	}
